from typing import List, Tuple, Set

BOARD_SIZE = 9
POSSIBLE_NUMBERS = set(i for i in range(1, 10))
ROW, COL = 0, 1
ITERS = 0


def possible_moves(board: List[List[str]], coords: Tuple[int, int]) -> Set[int]:
    if coords[ROW] >= BOARD_SIZE or coords[COL] >= BOARD_SIZE:
        return set()
    used_numbers = set()
    for i in range(len(board)):
        if board[coords[ROW]][i] != ".":
            used_numbers.add(int(board[coords[ROW]][i]))
        if board[i][coords[COL]] != ".":
            used_numbers.add(int(board[i][coords[COL]]))
        row_square = ((coords[ROW] // 3) * 3) + (i // 3)
        col_square = ((coords[COL] // 3) * 3) + (i % 3)
        if board[row_square][col_square] != ".":
            used_numbers.add(int(board[row_square][col_square]))
    return POSSIBLE_NUMBERS.difference(used_numbers)


def is_correct(board: List[List[str]], coords: Tuple[int, int]) -> bool:
    if coords[ROW] >= BOARD_SIZE or coords[COL] >= BOARD_SIZE:
        return False
    if any("." in sub for sub in board):
        return False
    used_numbers = set()
    for i in range(len(board)):
        if board[coords[ROW]][i] != ".":
            used_numbers.add(int(board[coords[ROW]][i]))
        if board[i][coords[COL]] != ".":
            used_numbers.add(int(board[i][coords[COL]]))
        row_square = ((coords[ROW] // 3) * 3) + (i // 3)
        col_square = ((coords[COL] // 3) * 3) + (i % 3)
        if board[row_square][col_square] != ".":
            used_numbers.add(int(board[row_square][col_square]))
    return used_numbers == POSSIBLE_NUMBERS


def solve(board: List[List[str]], coords: Tuple[int, int]):
    row, col = coords[ROW], coords[COL]
    if col >= BOARD_SIZE:
        row += 1
        col = 0
    while row < BOARD_SIZE and board[row][col] != ".":
        col += 1
        if col >= BOARD_SIZE:
            row += 1
            col = 0
    for num in possible_moves(board, (row, col)):
        board[row][col] = str(num)
        solve(board, (row, col + 1))
        if is_correct(board, (row, col)):
            return
        board[row][col] = "."


def main():
    board = [["5", "3", ".", ".", "7", ".", ".", ".", "."],
             ["6", ".", ".", "1", "9", "5", ".", ".", "."],
             [".", "9", "8", ".", ".", ".", ".", "6", "."],
             ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
             ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
             ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
             [".", "6", ".", ".", ".", ".", "2", "8", "."],
             [".", ".", ".", "4", "1", "9", ".", ".", "5"],
             [".", ".", ".", ".", "8", ".", ".", "7", "9"]]
    board_solved = [["5", "3", "4", "6", "7", "8", "9", "1", "2"], ["6", "7", "2", "1", "9", "5", "3", "4", "8"],
                    ["1", "9", "8", "3", "4", "2", "5", "6", "7"], ["8", "5", "9", "7", "6", "1", "4", "2", "3"],
                    ["4", "2", "6", "8", "5", "3", "7", "9", "1"], ["7", "1", "3", "9", "2", "4", "8", "5", "6"],
                    ["9", "6", "1", "5", "3", "7", "2", "8", "4"], ["2", "8", "7", "4", "1", "9", "6", "3", "5"],
                    ["3", "4", "5", "2", "8", "6", "1", "7", "9"]]
    solve(board, (0, 0))


if __name__ == '__main__':
    main()
