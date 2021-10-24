from typing import Tuple
from Coordinates import Coordinates
import pygame
import math
import multiprocessing as mp


class Statusbar:
    def __init__(self, coordinates: Coordinates, max_mp: float):
        self.color_hp = (230, 32, 18)
        self.color_mp = (0, 170, 255)
        self.percent_hp = mp.Value('i', 100)
        self.percent_mp = mp.Value('i', 100 if max_mp > 0 else 0)
        self.bar_size = 64
        self.bar_height = 5
        self.space = 3
        self.player_coordinates = coordinates
        x, y = coordinates.values()
        self.coordinates = Coordinates(x, y - 20)

    def update_hp(self, current: float, maximum: float):
        with self.percent_hp.get_lock():
            if current > 0:
                self.percent_hp.value = math.ceil(current / maximum * 100)

    def update_mp(self, current: float, maximum: float):
        with self.percent_mp.get_lock():
            if current > 0:
                self.percent_mp.value = math.ceil(current / maximum * 100)

    def display(self, screen):
        x, y = self.coordinates.values()
        size_hp = self.bar_size * (self.percent_hp.value / 100)
        size_mp = self.bar_size * (self.percent_mp.value / 100)
        # hp bar
        pygame.draw.rect(screen, self.color_hp, pygame.Rect(x, y, size_hp, self.bar_height))
        pygame.draw.rect(screen, (0, 0, 0), pygame.Rect(x + size_hp, y, self.bar_size - size_hp, self.bar_height))
        # mp bar
        pygame.draw.rect(screen, self.color_mp, pygame.Rect(x, y + self.bar_height + self.space, size_mp,
                                                            self.bar_height))
        pygame.draw.rect(screen, (0, 0, 0), pygame.Rect(x + size_mp, y + self.bar_height + self.space,
                                                        self.bar_size - size_mp, self.bar_height))
