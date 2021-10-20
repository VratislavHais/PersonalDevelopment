from typing import Tuple
from random import randint


class Coordinates:
    def __init__(self, x=0, y=0, rand=False, board_size=(760, 760)):
        if rand:
            self.x = randint(0, board_size[0])
            self.y = randint(0, board_size[1])
        else:
            self.x = x
            self.y = y

    def values(self):
        return self.x, self.y

    def update(self, coord: Tuple[int, int], max_size: Tuple[int, int]) -> bool:
        edge = False
        self.x += coord[0]
        if self.x < 0:
            self.x = 0
            edge = True
        elif self.x > max_size[0]:
            self.x = max_size[0]
            edge = True
        self.y += coord[1]
        if self.y < 0:
            self.y = 0
            edge = True
        elif self.y > max_size[1]:
            self.y = max_size[1]
            edge = True
        return edge

    def equals(self, other):
        if isinstance(other, Coordinates):
            return abs(self.x - other.x) <= 60 and abs(self.y - other.y) <= 60
        return False

    def is_in_list(self, list_to_scan):
        for enemy in list_to_scan:
            if self.equals(enemy.coordinates):
                return True
        return False

    def retrieve_from_list(self, list_to_scan):
        for enemy in list_to_scan:
            if self.equals(enemy.coordinates):
                return enemy
