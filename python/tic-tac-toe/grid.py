from typing import List
from collections import deque
from itertools import product

# for tuple addressing purposes
# better than magic numbers
row = 0
col = 1


class Grid:
    def __init__(self, size: int, winning_score: int, blank_char=" "):
        self.size = size
        self._winning_score = winning_score
        self._blank_char = blank_char
        self._grid = []
        self._victor = None
        self._moves_history = deque()
        for i in range(size):
            self._grid.append([])
            for j in range(size):
                self._grid[i].append(blank_char)

    def print_grid(self):
        columns = [f" {i}  " for i in range(1, self.size + 1)]
        print("  ", "".join(columns))
        for i, line in enumerate(self._grid):
            print("  +" + "---+" * self.size)
            print(str(i + 1), "|", " | ".join(line), "|")
        print("  +" + "---+" * self.size)

    def is_tie(self) -> bool:
        return self.empty_squares() == 0

    def is_valid_move(self, move_list: List[int]) -> bool:
        if len(move_list) < 2:
            return False
        return self._grid[move_list[row]][move_list[col]] == " "

    def _line_score(self, player_mark: str, move: List[int]) -> int:
        score = 0
        for i in range(self.size):
            if self._grid[move[row]][i] == player_mark:
                score += 1
        return score

    def _column_score(self, player_mark: str, move: List[int]) -> int:
        score = 0
        for i in range(self.size):
            if self._grid[i][move[col]] == player_mark:
                score += 1
        return score

    def _diag_score(self, player_mark: str, move: List[int]) -> int:
        score_diag = score_antidiag = 0
        if move[row] == move[col] or move[row] + move[col] + 1 == self.size:
            for i in range(self.size):
                if self._grid[i][i] == player_mark:
                    score_diag += 1
                if self._grid[i][self.size - 1 - i] == player_mark:
                    score_antidiag += 1
        return max(score_diag, score_antidiag)

    def is_victorious(self, player_mark: str, move: List[int]):
        if self._line_score(player_mark, move) == self._winning_score or \
               self._column_score(player_mark, move) == self._winning_score or \
               self._diag_score(player_mark, move) == self._winning_score:
            self._victor = player_mark

    def undo(self):
        prev_move = self._moves_history.pop()
        self._grid[prev_move[row]][prev_move[col]] = self._blank_char
        self._victor = None

    def victor(self) -> str:
        return self._victor

    def execute_move(self, move: List[int], player_mark: str):
        self._grid[move[row]][move[col]] = player_mark
        self._moves_history.append(move)
        self.is_victorious(player_mark, move)

    def empty_squares(self) -> int:
        return self.size * self.size - len(self._moves_history)

    def possible_moves(self) -> List[List[int]]:
        result = []
        for row in range(self.size):
            for col in range(self.size):
                if self._grid[row][col] == self._blank_char:
                    result.append([row, col])
        return result

    def possible_moves_optimized(self) -> List[List[int]]:
        result = []
        directions = [-1, 0, 1]
        for i in range(0, len(self._moves_history), 2):
            for direction in product(directions, repeat=2):
                move = [self._moves_history[i][row] + direction[row], self._moves_history[i][col] + direction[col]]
                if 0 <= move[row] < self.size and 0 <= move[col] < self.size and self.is_valid_move(move):
                    result.append(move)
        return result
