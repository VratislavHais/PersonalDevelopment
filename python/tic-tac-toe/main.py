import pickle
from typing import List
from itertools import cycle
from player import Player
from grid import Grid


def get_player_move(grid: Grid, player: Player) -> List[int]:
    move_list = []
    while not grid.is_valid_move(move_list):
        move_list = player.get_move(grid)
    return move_list


players = (Player("Player 1", "X"), Player("Player 2", "O", True))
winning_score = 4
grid_size = 5
# with open("grid.pickle", "rb") as file:
#     grid = pickle.load(file)
grid = Grid(grid_size, winning_score)
grid.print_grid()
for player in cycle(players):
    print(f"{player}'s move")
    current_move = get_player_move(grid, player)
    grid.execute_move(current_move, player.mark())
    grid.print_grid()
    if grid.victor():
        print(f"{grid.victor()} won. Congratulations!")
        break
    elif grid.is_tie():
        print("It's a tie!")
        break

# with open("grid.pickle", "wb") as file:
#     pickle.dump(grid, file, protocol=pickle.HIGHEST_PROTOCOL)
