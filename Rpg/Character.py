from abc import ABC, abstractmethod
from Attributes import Attributes
from random import randint
from ctypes import c_bool
from Statusbar import Statusbar
from typing import Tuple
import time
import Weapon
import multiprocessing as mp


class Character(ABC):
    def __init__(self, max_hp: float, max_mp: float, hp_regen: float, mp_regen: float, str_: int, int_: int,
                 agi: int, weapon: Weapon, coords=None):
        self.attributes = Attributes(max_hp, max_mp, hp_regen, mp_regen, str_, int_, agi)
        self.is_dead = mp.Value(c_bool, False)
        self.in_combat = mp.Value(c_bool, False)
        self.game_ended = mp.Value(c_bool, False)
        self.weapon = weapon
        self.coordinates = coords
        self.status_bar = Statusbar(coords, max_mp)
        self.run_regen()

    @property
    @abstractmethod
    def image(self):
        pass

    def to_combat(self):
        with self.in_combat.get_lock():
            self.in_combat.value = True

    def from_combat(self):
        with self.in_combat.get_lock():
            self.in_combat.value = False

    def set_dead(self):
        with self.is_dead.get_lock():
            self.is_dead.value = True

    def end_game(self):
        with self.game_ended.get_lock():
            self.game_ended.value = True

    def regen(self):
        while not self.is_dead.value and not self.game_ended.value:
            if not self.in_combat.value:
                self.health_regen(self.attributes.hp_regen)
                self.mana_regen(self.attributes.mp_regen)
            time.sleep(1)

    def health_regen(self, hp_regen: float):
        self.attributes.update_hp(hp_regen)
        self.update_hp_bar()

    def mana_regen(self, mp_regen: float):
        self.attributes.update_mp(mp_regen)
        self.update_mp_bar()

    def run_regen(self):
        process = mp.Process(target=self.regen, args=(), daemon=True)
        process.start()

    def hit(self, dmg: float):
        self.attributes.update_hp(-dmg)
        self.update_hp_bar()
        if self.attributes.hp.value <= 0:
            self.set_dead()

    def heal(self, hp: float):
        self.health_regen(hp)

    def mana_recovery(self, mp: float):
        self.mana_regen(mp)

    def attack(self, target) -> int:
        dodged = randint(0, 100) <= self.attributes.agility
        if not dodged:
            damage_done = (self.weapon.damage + randint(1, 10)) * (self.attributes.strength / 100 + 1)
            target.hit(damage_done)
            return damage_done
        else:
            return 0

    def display(self, screen):
        self.status_bar.display(screen)
        screen.blit(self.image, (self.coordinates.values()))

    def move(self, movement: Tuple[int, int], board_size: Tuple[int, int]):
        is_on_edge = self.coordinates.update(movement, board_size)
        if not is_on_edge:
            self.status_bar.update_coordinates(movement, board_size)

    def update_hp_bar(self):
        self.status_bar.update_hp(self.attributes.hp.value, self.attributes.max_hp.value)

    def update_mp_bar(self):
        self.status_bar.update_mp(self.attributes.mp.value, self.attributes.max_mp.value)

    def __str__(self):
        return self.name + " (" + self.__class__.__name__ + "):\n" +\
            str(self.attributes)
