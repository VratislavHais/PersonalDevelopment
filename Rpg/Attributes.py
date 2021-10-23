import math
from multiprocessing import Value


class Attributes:
    def __init__(self, max_hp: float, max_mp: float, str_: int, int_: int, agi: int):
        self.max_hp = Value('f', max_hp)
        self.hp = Value('f', max_hp)
        self.max_mp = Value('f', max_mp)
        self.mp = Value('f', max_mp)
        self.strength = str_
        self.intelligence = int_
        self.agility = agi
        self.xp = 0
        self.xp_to_lvl = 100

    def _level_up(self):
        self._update_max_hp(math.ceil(self.max_hp.value / 10))
        self._update_max_hp(math.ceil(self.max_mp.value / 10))
        self.strength += math.ceil(self.strength / 10)
        self.intelligence += math.ceil(self.intelligence / 10)
        self.agility += math.ceil(self.agility / 10)
        self.update_hp(self.max_hp)
        self.update_mp(self.max_mp)
        self.xp_to_lvl *= 2

    def _update_max_hp(self, value: float):
        with self.max_hp.get_lock():
            self.max_hp.value += value

    def _update_max_mp(self, value: float):
        with self.max_mp.get_lock():
            self.max_mp.value += value

    def update_hp(self, value: float):
        with self.hp.get_lock():
            if self.hp.value + value > self.max_hp.value:
                self.hp.value = self.max_hp.value
            else:
                self.hp.value += value
            
    def update_mp(self, value: float):
        with self.mp.get_lock():
            if self.mp.value + value > self.max_mp.value:
                self.mp.value = self.max_mp.value
            else:
                self.mp.value += value

    def gain_xp(self, xp: int):
        self.xp += xp
        if self.xp >= self.xp_to_lvl:
            self._level_up()

    def __str__(self):
        return "Maximum HP: " + str(self.max_hp.value) + "\n" +\
            "Maximum MP: " + str(self.max_mp.value) + "\n" +\
            "Strength: " + str(self.strength) + "\n" +\
            "Intelligence: " + str(self.intelligence) + "\n" +\
            "Agility: " + str(self.agility) + "\n"
