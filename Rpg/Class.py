from Character import Character
import Weapon
import pygame
from Coordinates import Coordinates


class Warrior(Character):
    image = pygame.image.load("images/warrior.png")
    
    def __init__(self, name: str, coordinates: Coordinates):
        super().__init__(200, 0, 3, 0, 25, 6, 10, Weapon.RustySword, coordinates)
        self.name = name


class Mage(Character):
    image = pygame.image.load("images/wizard.png")
    
    def __init__(self, name: str, coordinates: Coordinates):
        super().__init__(100, 50, 1, 1, 6, 25, 10, Weapon.Fist, coordinates)
        self.name = name


class Rogue(Character):
    image = pygame.image.load("images/rogue.png")

    def __init__(self, name: str, coordinates: Coordinates):
        super().__init__(120, 20, 1.5, 1, 10, 6, 25, Weapon.Dagger, coordinates)
        self.name = name
