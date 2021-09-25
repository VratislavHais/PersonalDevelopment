from typing import List
from collections import namedtuple
from itertools import cycle
from player import Player


def print_grid(grid: List[List[str]]):
    columns = [f" {i}  " for i in range(1, len(grid) + 1)]
    print("  ", "".join(columns))
    for i, line in enumerate(grid):
        print("  +" + "---+" * len(line))
        print(str(i + 1), "|", " | ".join(line), "|")
    print("  +" + "---+" * len(grid))


def is_grid_full(grid: List[List[str]]) -> bool:
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == " ":
                return False
    return True


def line_score(grid: List[List[str]], player_mark: str, move: namedtuple("Move", "row,col")) -> int:
    score = 1
    for i in range(len(grid)):
        if i == move.col:
            continue
        if grid[move.row][i] == player_mark:
            score += 1
    return score


def column_score(grid: List[List[str]], player_mark: str, move: namedtuple("Move", "row,col")) -> int:
    score = 1
    for i in range(len(grid)):
        if i == move.row:
            continue
        if grid[i][move.col] == player_mark:
            score += 1
    return score


def diag_score(grid: List[List[str]], player_mark: str, move: namedtuple("Move", "row,col")) -> int:
    score_diag = score_antidiag = 0
    if move.row == move.col or move.row + move.col == len(grid):
        for i in range(len(grid)):
            if grid[i][i] == player_mark:
                score_diag += 1
            if grid[i][len(grid) - 1 - i] == player_mark:
                score_antidiag += 1
    return max(score_diag, score_antidiag)


def is_victorious(grid: List[List[str]], player_mark: str, move: namedtuple("Move", "row,col"), winning_score: int) -> bool:
    return line_score(grid, player_mark, move) == winning_score or \
           column_score(grid, player_mark, move) == winning_score or \
           diag_score(grid, player_mark, move) == winning_score


def init_grid(grid_size: int) -> List[List[str]]:
    grid = []
    for i in range(grid_size):
        grid.append([])
        for j in range(grid_size):
            grid[i].append(" ")
    return grid


def is_valid_move(grid: List[List[str]], move_list: List[int]) -> bool:
    if len(move_list) < 2:
        return False
    return grid[move_list[0]][move_list[1]] == " "


def get_player_move(grid: List[List[str]], player: Player) -> namedtuple("Move", "row,col"):
    move_list = []
    while not is_valid_move(grid, move_list):
        move_list = player.get_move(grid)
    return Move(*move_list)


def execute_move(grid: List[List[str]], move: namedtuple("Move", "row,col"), player_mark: str):
    grid[move.row][move.col] = player_mark


players = (Player("Player 1", "X"), Player("Player 2", "O", True))
winning_score = grid_size = 3
Move = namedtuple("Move", "row,col")
grid = init_grid(grid_size)
print_grid(grid)
for player in cycle(players):
    print(f"{player}'s move")
    current_move = get_player_move(grid, player)
    execute_move(grid, current_move, player.mark())
    print_grid(grid)
    if is_victorious(grid, player.mark(), current_move, winning_score):
        print(f"{player} won. Congratulations!")
        break
