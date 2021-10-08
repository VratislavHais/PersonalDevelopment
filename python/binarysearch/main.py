from random import randint
from typing import List


def binarysearch(numbers: List[int], target: int) -> int:
    i, j = 0, len(numbers)
    while i <= j:
        mid = (i + j) >> 1
        if numbers[mid] == target:
            return mid
        elif numbers[mid] < target:
            i = mid + 1
        else:
            j = mid - 1
    return i


numbers = [randint(0, 100) for i in range(50)]
numbers.sort()
print(numbers)
print(binarysearch(numbers, 10))

