from typing import Tuple
from random import randint


class Coordinates:
    def __init__(self, x=0, y=0, rand=False, board_size=(740, 740)):
        self.board_size = board_size
        if rand:
            self.x = randint(0, board_size[0])
            self.y = randint(0, board_size[1])
        else:
            self.x = x
            self.y = y

    def values(self):
        return self.x, self.y

    def update(self, coord: Tuple[int, int]):
        self.x += coord[0]
        if self.x < 0:
            self.x = 0
        elif self.x > self.board_size[0]:
            self.x = self.board_size[0]
        self.y += coord[1]
        if self.y < 0:
            self.y = 0
        elif self.y > self.board_size[1]:
            self.y = self.board_size[1]

    def over_border(self, update: Tuple[int, int]) -> bool:
        return self.x + update[0] < 0 or self.x + update[0] > self.board_size[0] or\
                self.y + update[1] < 0 or self.y + update[1] > self.board_size[1]
        # return self.board_size[0] < self.x + update[0] < 0 or self.board_size[1] < self.y + update[1] < 0

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
