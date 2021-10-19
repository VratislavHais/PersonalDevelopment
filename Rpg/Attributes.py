import math
from multiprocessing import Value


class Attributes:
    def __init__(self, max_hp: float, max_mp: float, hp_regen: float, mp_regen: float, str_: int, int_: int, agi: int):
        self.max_hp = max_hp
        self.hp = Value('f', max_hp)
        self.max_mp = max_mp
        self.mp = Value('f', max_mp)
        self.hp_regen = hp_regen
        self.mp_regen = mp_regen
        self.strength = str_
        self.intelligence = int_
        self.agility = agi
        self.xp = 0
        self.xp_to_lvl = 100

    def _level_up(self):
        self.max_hp += math.ceil(self.max_hp / 10)
        self.max_mp += math.ceil(self.max_mp / 10)
        self.strength += math.ceil(self.strength / 10)
        self.intelligence += math.ceil(self.intelligence / 10)
        self.agility += math.ceil(self.agility / 10)
        self.update_hp(self.max_hp)
        self.update_mp(self.max_mp)
        self.xp_to_lvl *= 2

    def update_hp(self, value: float):
        with self.hp.get_lock():
            if self.hp.value + value > self.max_hp:
                self.hp.value = self.max_hp
            else:
                self.hp.value += value
            
    def update_mp(self, value: float):
        with self.mp.get_lock():
            if self.mp.value + value > self.max_mp:
                self.mp.value = self.max_mp
            else:
                self.mp.value += value

    def gain_xp(self, xp: int):
        self.xp += xp
        if self.xp >= self.xp_to_lvl:
            self._level_up()

    def __str__(self):
        return "Maximum HP: " + str(self.max_hp) + "\n" +\
            "Maximum MP: " + str(self.max_mp) + "\n" +\
            "Strength: " + str(self.strength) + "\n" +\
            "Intelligence: " + str(self.intelligence) + "\n" +\
            "Agility: " + str(self.agility) + "\n"
