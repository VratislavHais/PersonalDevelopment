import math
from typing import List
from random import randint
from grid import Grid


class Player:
    def __init__(self, player_name: str, player_mark: str, is_ai=False):
        self._player_name = player_name
        self._mark = player_mark
        self._is_ai = is_ai

    def __str__(self):
        return f"{self._player_name} ({self._mark} mark)"

    def mark(self):
        return self._mark

    @staticmethod
    def _get_manual_move(grid: Grid) -> List[int]:
        move_list = []
        for i in ("row", "col"):
            player_input = input(f"Choose {i}: ")
            while not player_input or not player_input.isdigit() or not (0 < int(player_input) <= grid.size):
                print("Incorrect input.")
                player_input = input(f"Choose {i}: ")
            move_list.append(int(player_input) - 1)
        return move_list

    def _get_ai_move(self, grid: Grid, current_mark: str) -> dict:
        other_player = "O" if "X" == current_mark else "X"
        if grid.victor() == other_player:
            return {"move": None, "score": (1 if other_player == self._mark else -1) * (grid.empty_squares() + 1)}
        elif grid.is_tie():
            return {"move": None, "score": 0}
        best = {
            "move": None,
            "score": -math.inf if current_mark == self._mark else math.inf
        }
        for move in grid.possible_moves():
            grid.execute_move(move, current_mark)
            minmax = self._get_ai_move(grid, other_player)
            minmax["move"] = move
            if current_mark == self._mark:
                if minmax["score"] > best["score"]:
                    best = minmax
            else:
                if minmax["score"] < best["score"]:
                    best = minmax
            grid.undo()
        return best

    def get_move(self, grid: Grid) -> List[int]:
        if not self._is_ai:
            move_list = self._get_manual_move(grid)
        else:
            move_list = self._get_ai_move(grid, self._mark, 5)["move"]
        return move_list
