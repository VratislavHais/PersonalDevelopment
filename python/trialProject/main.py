from collections import Counter, namedtuple, defaultdict, deque
from itertools import product, permutations, combinations, accumulate, groupby, count, repeat, cycle
import random
import secrets  # true random numbers
import numpy as np
import functools
from abc import ABC, abstractmethod


# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
class CountCalls:
    def __init__(self, function):
        self.function = function
        self.num_of_calls = 0

    def __call__(self, *args, **kwargs):
        self.num_of_calls += 1
        print(f"Number of executions: {self.num_of_calls}")
        return self.function(*args, **kwargs)

    def get_num_calls(self) -> int:
        return self.num_of_calls


def print_hi(name: str):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

a = "aaaabbbdssfaew"
counter = Counter(a)
print(list(counter))

Point = namedtuple('Point', "x,y")
point = Point(3, 4)
print(point.x, point.y)

# OrderedDict deprecated since python 3.7

dt = defaultdict(int)
dt["a"] = 1
print(dt["b"])
print(dt["a"])

queue = deque()
queue.append(1)
queue.appendleft(2)
queue.append(3)
queue.extend([5, 6, 7])
queue.extendleft([7, 8, 9])
print(queue)

# kartezsky soucin
a = [1, 2, 5]
b = [3, 4]
print("Product:")
print(list(product(a, b)))

print("Permutations:")
print(list(permutations(a)))

print("Combinations:")
print(list(combinations(a, 2)))

# prefixed sum
print("Accumulate:")
print(list(accumulate(a)))

Person = namedtuple("Person", "name,surname,age")
people = [Person("Vratislav", "Hais", 26), Person("Gabriela", "Strasilova", 24), Person("Tomas", "Veskrna", 26)]
people.sort(key=lambda person: person.age)
byage = groupby(people, lambda person: person.age)
for key, value in byage:
    print(key, list(value))

# increment indefinitely
# for i in count(10):
#     print(i)
#     if i == 20:
#         break

# repeats 1 indefinitely
# for i in repeat(1):
#     print(1)

# indefinitely repeats the list
# a = [1, 2, 3]
# for i in repeat(a):
#     print(a)

str_list = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
print(random.random())  # random float from range 0 to 1
print(random.uniform(1, 20))  # random float from specific range
print(random.randint(1, 10))  # random int from range (included)
print(random.randrange(1, 10))  # random int from range (upperbound)
print(random.choice(str_list))  # random item from list
print(random.sample(str_list, 3))  # random items from list - number specified by int
print(random.choices(str_list, k=3))  # random items from list - repeated
random.shuffle(str_list)  # shuffle the array
print(str_list)

print(secrets.randbelow(10))
print(secrets.randbits(4))  # number with 4 bits

np.random.rand(3)


################## Decorator ##################


def custom_decorator(multiply_by):
    @functools.wraps(multiply_by)
    def wrapper(*args, **kwargs):
        print(f"Multiply {args[0]} by {args[1]}")
        return multiply_by(*args, **kwargs)

    return wrapper


def debug(function):
    @functools.wraps(function)
    def wrapper(*args, **kwargs):
        args_representation = [repr(arg) for arg in args]
        kwargs_representation = [f"{k}={v!r}" for k, v in kwargs]
        signature = ", ".join(args_representation + kwargs_representation)
        print(f"Calling {function.__name__}({signature})")
        return_value = function(*args, **kwargs)
        print(f"{function.__name__} returned {return_value!r}")
        return return_value

    return wrapper


@debug
@custom_decorator
def multiply_by(x: int, y: int):
    return x * y


print(multiply_by(5, 6))


def repeat(num_times: int):
    def decorator(greet):
        @functools.wraps(greet)
        def wrapper(*args, **kwargs):
            for _ in range(0, num_times):
                greet(*args, **kwargs)

        return wrapper

    return decorator

@repeat(num_times=4)
@CountCalls
def greet(name: str):
    print(f"Hello {name}")

greet("Vratislav")

set = {1,}
print(type(set))

directions = [-1, 0, 1]
print(list(product(directions, repeat=2)))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
