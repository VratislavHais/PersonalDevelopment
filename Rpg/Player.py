from typing import Tuple
from Character import Character
import Weapon
import Spell
import pygame
from abc import ABC
from Coordinates import Coordinates
import time
import multiprocessing as mp


class Player(Character, ABC):
    def __init__(self, max_hp: float, max_mp: float, hp_regen: float, mp_regen: float, str_: int, int_: int,
                 agi: int, weapon: Weapon, coords=None, spells=None):
        super().__init__(max_hp, max_mp, str_, int_, agi, weapon, coords, spells)
        self.hp_regen = hp_regen
        self.mp_regen = mp_regen
        self.run_regen()
    
    def regen(self):
        while not self.is_dead.value and not self.game_ended.value:
            if not self.in_combat.value:
                self.health_regen(self.hp_regen)
                self.mana_regen(self.mp_regen)
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

    def mana_recovery(self, mp: float):
        self.mana_regen(mp)

    def heal(self, hp: float):
        self.health_regen(hp)

    def attack_or_spell(self, target: Character):
        print("Types of attacks available:")
        print("1) Attack with Weapon")
        print("2) Cast a Spell")
        decision = input("Pick your attack: ")
        while not decision.isdigit() or (int(decision) != 1 and int(decision) != 2):
            print("Invalid input!")
            decision = input("Pick your attack: ")
        if int(decision) == 1:
            self.weapon_attack(target)
        else:
            spell_to_cast = self.pick_spell()
            while spell_to_cast != 0 and self.spells[spell_to_cast-1].mana_consumption > self.attributes.mp.value:
                print("Insufficient mana!")
                spell_to_cast = self.pick_spell()
            if spell_to_cast == 0:
                self.attack_or_spell(target)
            else:
                self.cast_spell(spell_to_cast - 1, target)

    def add_xp(self, xp: int):
        self.attributes.gain_xp(xp)

    def pick_spell(self) -> int:
        print("Mana available: " + str(self.attributes.mp.value))
        print("Spells:")
        print("0) Return to Attack Select")
        for i, spell in enumerate(self.spells):
            print(str(i + 1) + ") " + str(spell))
        picked = input("Pick your spell: ")
        while not isinstance(picked, int) and (int(picked) > len(self.spells) or int(picked) < 0):
            print("Invalid input!")
            picked = input("Pick your spell: ")
        return int(picked)

    def move(self, x: int, y: int):
        updates = ((x, 0), (0, y))
        for update in updates:
            if not (self.status_bar.coordinates.over_border(update) or self.coordinates.over_border(update)):
                self.coordinates.update(update)
                self.status_bar.coordinates.update(update)


class Warrior(Player):
    image = pygame.image.load("images/warrior.png")
    
    def __init__(self, name: str, coordinates: Coordinates):
        super().__init__(200, 0, 3, 0, 25, 6, 10, Weapon.RustySword, coordinates)
        self.name = name


class Mage(Player):
    image = pygame.image.load("images/wizard.png")
    
    def __init__(self, name: str, coordinates: Coordinates):
        super().__init__(100, 50, 1, 1, 6, 25, 10, Weapon.Fist, coordinates,
                         [Spell.Fireball(), Spell.MagicMissile(), Spell.Heal()])
        self.name = name


class Rogue(Player):
    image = pygame.image.load("images/rogue.png")

    def __init__(self, name: str, coordinates: Coordinates):
        super().__init__(120, 20, 1.5, 1, 10, 6, 25, Weapon.Dagger, coordinates)
        self.name = name
