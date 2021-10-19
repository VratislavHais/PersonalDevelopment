from typing import Tuple


class Coordinates:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    def values(self):
        return self.x, self.y

    def update(self, coord: Tuple[int, int], max_size: Tuple[int, int]):
        self.x += coord[0]
        if self.x < 0:
            self.x = 0
        elif self.x > max_size[0]:
            self.x = max_size[0]
        self.y += coord[1]
        if self.y < 0:
            self.y = 0
        elif self.y > max_size[1]:
            self.y = max_size[1]
