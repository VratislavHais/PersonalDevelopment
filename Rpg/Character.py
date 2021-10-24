from abc import ABC, abstractmethod
from Attributes import Attributes
from random import randint
from ctypes import c_bool
from Statusbar import Statusbar
from Screen import Screen
import Weapon
import multiprocessing as mp


class Character(ABC):
    def __init__(self, max_hp: float, max_mp: float, str_: int, int_: int,
                 agi: int, weapon: Weapon, coords=None, spells=None, xp_bar=False):
        self.attributes = Attributes(max_hp, max_mp, str_, int_, agi)
        self.is_dead = mp.Value(c_bool, False)
        self.in_combat = mp.Value(c_bool, False)
        self.game_ended = mp.Value(c_bool, False)
        self.weapon = weapon
        self.coordinates = coords
        self.spells = spells
        self.status_bar = Statusbar(coords, max_mp, xp_bar)

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

    def hit(self, dmg: float):
        self.attributes.update_hp(-dmg)
        self.update_hp_bar()
        if self.attributes.hp.value <= 0:
            self.set_dead()

    @abstractmethod
    def attack_or_spell(self, target):
        pass

    @abstractmethod
    def pick_spell(self):
        pass

    def weapon_attack(self, target) -> int:
        dodged = randint(0, 100) <= self.attributes.agility
        if not dodged:
            damage_done = (self.weapon.damage + randint(1, 10)) * (self.attributes.strength / 100 + 1)
            target.hit(damage_done)
            return damage_done
        else:
            return 0

    def cast_spell(self, spell: int, target):
        self.attributes.update_mp(-self.spells[spell].mana_consumption)
        self.update_mp_bar()
        if self.spells[spell].__class__.__name__ == "Heal":
            target = self
        damage = (self.spells[spell].damage + randint(1, 20)) * (self.attributes.intelligence / 100 + 1)
        target.hit(damage)

    def display(self):
        self.status_bar.display(Screen.get_screen())
        Screen.get_screen().blit(self.image, (self.coordinates.values()))

    def update_hp_bar(self):
        self.status_bar.update_hp(self.attributes.hp.value, self.attributes.max_hp.value)

    def update_mp_bar(self):
        self.status_bar.update_mp(self.attributes.mp.value, self.attributes.max_mp.value)

    def __str__(self):
        return self.name + " (" + self.__class__.__name__ + "):\n" + \
               str(self.attributes)
