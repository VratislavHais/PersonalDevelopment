from typing import List
from random import randint
from itertools import product

row, col = 0, 1
surroundings = [i for i in product((-1, 0, 1), repeat=2) if i != (0, 0)]


class Board:
    def __init__(self, board_size: int, num_bombs: int):
        self.__board_size = board_size
        self.__num_bombs = num_bombs
        self.__board = self.__generate_board()
        self.__revealed = [[False for _ in range(board_size)] for _ in range(board_size)]
        self.__num_revealed = 0
        self.__precompute_board()
        self.__game_over = False

    def __generate_board(self) -> List[List[str]]:
        board = [[" " for _ in range(self.__board_size)] for _ in range(self.__board_size)]
        bombs_placed = 0
        while bombs_placed < self.__num_bombs:
            index = randint(0, self.__board_size * self.__board_size - 1)
            if board[index // self.__board_size][index % self.__board_size] != "*":
                board[index // self.__board_size][index % self.__board_size] = "*"
                bombs_placed += 1
        return board

    def __precompute_board(self):
        for r in range(self.__board_size):
            for c in range(self.__board_size):
                if self.__board[r][c] == "*":
                    continue
                self.__board[r][c] = str(self.__check_number_of_neighbouring_bombs(r, c))

    def __check_number_of_neighbouring_bombs(self, r: int, c: int) -> int:
        bombs_found = 0
        for direction in surroundings:
            if self.__is_bomb(r + direction[row], c + direction[col]):
                bombs_found += 1
        return bombs_found

    def __is_bomb(self, r: int, c: int) -> bool:
        if not self.coordinates_correct([r, c]):
            return False
        return self.__board[r][c] == "*"

    def dig(self, coordinates: List[int]):
        r, c = coordinates[row], coordinates[col]
        if self.coordinates_correct(coordinates):
            if self.__is_bomb(r, c):
                self.__game_over = True
            elif not self.__revealed[r][c]:
                self.__revealed[r][c] = True
                self.__num_revealed += 1
                if self.__board[r][c] == "0":
                    for i in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                        self.dig([r + i[row], c + i[col]])

    def dug_bomb(self, coordinates: List[int]) -> bool:
        self.dig(coordinates)
        return self.__game_over

    def is_victorious(self) -> bool:
        return not self.__game_over and self.__num_revealed + self.__num_bombs == self.__board_size * self.__board_size

    def coordinates_correct(self, coordinates: List[int]) -> bool:
        return 0 <= coordinates[row] < self.__board_size and 0 <= coordinates[col] < self.__board_size

    def print_board(self, masked=True):
        columns = [f" {i}  " for i in range(1, self.__board_size + 1)]
        print("\t", "".join(columns))
        for i, line in enumerate(self.__board):
            print("\t+" + "---+" * self.__board_size)
            if masked:
                print(str(i + 1), "\t|", " | ".join([x if self.__revealed[i][j] else " " for j, x in enumerate(line)]),
                      "|")
            else:
                print(str(i + 1), "\t|", " | ".join(line), "|")
        print("\t+" + "---+" * self.__board_size)
