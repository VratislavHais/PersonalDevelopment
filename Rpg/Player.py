from Character import Character
import Weapon
import Spell
import pygame
from abc import ABC
from Coordinates import Coordinates
from Menu import Menu
import time
import multiprocessing as mp


class Player(Character, ABC):
    def __init__(self, max_hp: float, max_mp: float, hp_regen: float, mp_regen: float, str_: int, int_: int,
                 agi: int, weapon: Weapon, coords=None, spells=None):
        super().__init__(max_hp, max_mp, str_, int_, agi, weapon, coords, spells, True)
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
        decision = Menu.get_input(("Attack with Weapon", "Cast a Spell"))
        if decision == 0:
            self.weapon_attack(target)
        else:
            spell_to_cast = self.pick_spell()
            while spell_to_cast != len(self.spells) and \
                    self.spells[spell_to_cast - 1].mana_consumption > self.attributes.mp.value:
                Menu.show_message("Insufficient Mana!")
                spell_to_cast = self.pick_spell()
            if spell_to_cast == len(self.spells):
                self.attack_or_spell(target)
            else:
                self.cast_spell(spell_to_cast, target)

    def add_xp(self, xp: int):
        self.attributes.gain_xp(xp)
        self.status_bar.update_xp(self.attributes.xp, self.attributes.xp_to_lvl)

    def pick_spell(self) -> int:
        spells_str = [spell.__class__.__name__ for spell in self.spells]
        spells_str.append("Back")
        return Menu.get_input(spells_str)

    def move(self, x: int, y: int):
        updates = ((x, 0), (0, y))
        for update in updates:
            if not (self.status_bar.coordinates.over_border(update) or self.coordinates.over_border(update)):
                self.coordinates.update(update)
                self.status_bar.coordinates.update(update)


class Warrior(Player):
    image = pygame.image.load("images/warrior.png")

    def __init__(self, coordinates: Coordinates):
        super().__init__(200, 0, 3, 0, 25, 6, 10, Weapon.RustySword, coordinates)


class Mage(Player):
    image = pygame.image.load("images/wizard.png")

    def __init__(self, coordinates: Coordinates):
        super().__init__(100, 50, 1, 1, 6, 25, 10, Weapon.Fist, coordinates,
                         [Spell.Fireball(), Spell.MagicMissile(), Spell.Heal()])


class Rogue(Player):
    image = pygame.image.load("images/rogue.png")

    def __init__(self, coordinates: Coordinates):
        super().__init__(120, 20, 1.5, 1, 10, 6, 25, Weapon.Dagger, coordinates)


class ClassesFactory:
    def __init__(self):
        # self._classes = [m[0] for m in inspect.getmembers(Class, inspect.isclass) if m[1].__module__ == "Class"]
        self._classes = (Warrior, Mage, Rogue)

    def available_classes(self):
        return self._classes

    def available_classes_as_string(self):
        result = []
        for i in self._classes:
            result.append(i.__name__)
        return result

    def pick_class(self, picked_class: int, coordinates) -> Player:
        return self._classes[picked_class](coordinates)

    def __str__(self):
        result = ""
        for i, c in enumerate(self._classes):
            result += str(i + 1) + ") " + c.__name__ + "\n"
        return result

    def __len__(self):
        return len(self._classes)
