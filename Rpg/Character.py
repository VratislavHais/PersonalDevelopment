from abc import ABC, abstractmethod
from Attributes import Attributes
from random import randint
from ctypes import c_bool
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
            print("running regen")
            if not self.in_combat:
                self.health_regen(self.attributes.hp_regen)
                self.mana_regen(self.attributes.mp_regen)
            time.sleep(1)

    def health_regen(self, hp_regen: float):
        self.attributes.update_hp(hp_regen)

    def mana_regen(self, mp_regen: float):
        self.attributes.update_mp(mp_regen)

    def run_regen(self):
        process = mp.Process(target=self.regen, args=(), daemon=True)
        process.start()

    def hit(self, dmg: float):
        self.attributes.hp -= dmg
        if self.attributes.hp <= 0:
            self.is_dead = True

    def heal(self, hp: float):
        self.health_regen(hp)

    def mana_recovery(self, mp: float):
        self.mana_regen(mp)

    def attack(self, target):
        damage_done = (self.weapon.damage + randint(1, 10)) * (self.attributes.strength / 100 + 1)
        target.hit(damage_done)

    def __str__(self):
        return self.name + " (" + self.__class__.__name__ + "):\n" +\
            str(self.attributes)
