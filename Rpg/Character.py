from abc import ABC, abstractmethod
from threading import Thread
from time import sleep
from Attributes import Attributes
from random import randint
import Weapon


class Character(ABC):
    def __init__(self, max_hp: float, max_mp: float, hp_regen: float, mp_regen: float, str_: int, int_: int,
                 agi: int, weapon: Weapon, coords=None):
        self.attributes = Attributes(max_hp, max_mp, hp_regen, mp_regen, str_, int_, agi)
        self.is_dead = False
        self.in_combat = False
        self.weapon = weapon
        self.coordinates = coords
        self.run_regen()

    @property
    @abstractmethod
    def image(self):
        pass

    def regen(self):
        print("running regen")
        while not self.is_dead:
            if not self.in_combat:
                self.health_regen(self.attributes.hp_regen)
                self.mana_regen(self.attributes.mp_regen)
            sleep(1)

    def health_regen(self, hp_regen: float):
        self.attributes.hp += hp_regen
        if self.attributes.hp > self.attributes.max_hp:
            self.attributes.hp = self.attributes.max_hp

    def mana_regen(self, mp_regen: float):
        self.attributes.mp += mp_regen
        if self.attributes.mp > self.attributes.max_mp:
            self.attributes.mp = self.attributes.max_mp

    def run_regen(self):
        thread = Thread(target=self.regen)
        thread.start()

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
