from typing import List
from random import randint
from secrets import randbelow

fib_cache = [0, 1]


def bubble_sort(nums: List[int]) -> List[int]:
    change_made = True
    while change_made:
        change_made = False
        for i in range(0, len(nums) - 1):
            if nums[i] > nums[i + 1]:
                tmp = nums[i]
                nums[i] = nums[i + 1]
                nums[i + 1] = tmp
                change_made = True
    return nums


def star_triangle(height: int):
    for i in range(height):
        print(" " * (height - i) + "*" * (2 * i + 1))


# recursive
def fibonacci(index: int, cache=[0, 1]) -> int:
    if index <= 1:
        return cache[index]
    if len(cache) > index:
        return cache[index]
    cache.append(fibonacci(index - 1) + fibonacci(index - 2))
    return cache[index]


def fibonacci_sequential(index: int) -> int:
    while len(fib_cache) <= index:
        fib_cache.append(fib_cache[len(fib_cache) - 1] + fib_cache[len(fib_cache) - 2])
    return fib_cache[index]


def is_prime(number: int) -> bool:
    primes = [False if i < 2 else True for i in range(number + 1)]
    for i in range(2, number + 1):
        if primes[i]:
            idx = i * 2
            while idx < len(primes):
                primes[idx] = False
                idx += i
    return primes[number]


def is_palindrome(palindrome: str) -> bool:
    start, end = 0, len(palindrome) - 1
    while start <= end:
        if palindrome[start] != palindrome[end]:
            return False
        start += 1
        end -= 1
    return True


def is_palindrome_better(palindrome: str) -> bool:
    return palindrome == palindrome[::-1]


def count_capitals_in_file(file: str) -> int:
    with open(file, "r") as file:
        return sum(1 for line in file for c in line if c.isupper())


def sortArrayByParityII(nums: List[int]) -> List[int]:
    for i in range(len(nums)):
        if i & 1 != nums[i] & 1:
            parity = nums[i] & 1
            j = i + 1
            while j & 1 == nums[j] & 1 or nums[j] & 1 == parity:
                j += 1
            tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
    return nums


def get_sum(nums: List[int], target: int, idx: int):
    if target == 0:
        return []
    elif target < 0:
        return None
    for i in range(idx, len(nums)):
        result = get_sum(nums, target - nums[idx], i)
        if result is not None:
            return result.append(nums[idx])
    return None


def canPartitionKSubsets(nums: List[int], k: int) -> bool:
    target, mod = divmod(sum(nums), k)
    if mod > 0:
        return False
    while len(nums) > 0:
        remove = get_sum(nums, target, 0)
        if remove is None:
            return False
        for item in remove:
            nums.remove(item)
    return True



def main():
    # nums = [randbelow(10000) for _ in range(100)]
    # print(bubble_sort(nums))
    # star_triangle(30)
    # print(fibonacci(15))
    # print(fibonacci_sequential(15))
    # print(is_prime(11))
    # print(is_palindrome("aabaa"))
    # print(is_palindrome_better("aabaa"))
    # print(count_capitals_in_file("file.txt"))
    # print(sortArrayByParityII([648,831,560,986,192,424,997,829,897,843]))
    print(canPartitionKSubsets([2, 2, 2, 2, 4, 5], 4))


if __name__ == '__main__':
    main()
