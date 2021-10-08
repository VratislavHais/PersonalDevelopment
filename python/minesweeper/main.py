from board import Board
from typing import List


def get_input(row_col: str) -> int:
    while True:
        try:
            return int(input(f"Choose {row_col}: ")) - 1
        except ValueError:
            print("Invalid Input")


def get_player_move(board: Board) -> List[int]:
    row = get_input("row")
    col = get_input("col")
    while not board.coordinates_correct([row, col]):
        print("Invalid Input")
        row = get_input("row")
        col = get_input("col")
    return [row, col]


def main():
    board = Board(10, 10)
    while not board.is_victorious():
        board.print_board()
        coordinate = get_player_move(board)
        if board.dug_bomb(coordinate):
            break

    board.print_board(False)
    if board.is_victorious():
        print("Congratulations! You have won.")
    else:
        print("You've dug a mine and died painfully")


if __name__ == "__main__":
    main()
