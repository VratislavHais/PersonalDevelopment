from typing import List
from random import randint


class Player:
    def __init__(self, player_name: str, player_mark: str, is_ai=False):
        self._player_name = player_name
        self._mark = player_mark
        self._is_ai = is_ai

    def __str__(self):
        return f"{self._player_name} ({self._mark} mark)"

    def mark(self):
        return self._mark

    def _get_manual_move(self, grid: List[List[str]]) -> List[int]:
        move_list = []
        for i in ("row", "col"):
            player_input = input(f"Choose {i}: ")
            while not player_input or not player_input.isdigit() or not (0 < int(player_input) <= len(grid)):
                print("Incorrect input.")
                player_input = input(f"Choose {i}: ")
            move_list.append(int(player_input) - 1)
        return move_list

    def _get_ai_move(self, grid: List[List[str]]) -> List[int]:
        return [randint(0, len(grid) - 1), randint(0, len(grid) - 1)]

    def get_move(self, grid: List[List[str]]) -> List[int]:
        if not self._is_ai:
            move_list = self._get_manual_move(grid)
        else:
            move_list = self._get_ai_move(grid)
        return move_list
