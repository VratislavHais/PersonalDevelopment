from typing import Tuple
from Character import Character
import Class


class ClassesFactory:
    def __init__(self):
        # self._classes = [m[0] for m in inspect.getmembers(Class, inspect.isclass) if m[1].__module__ == "Class"]
        self._classes = (Class.Warrior, Class.Mage, Class.Rogue)

    def available_classes(self) -> Tuple:
        return self._classes

    def pick_class(self, picked_class: int, name: str, coordinates) -> Character:
        return self._classes[picked_class](name, coordinates)

    def __str__(self):
        result = ""
        for i, c in enumerate(self._classes):
            result += str(i + 1) + ") " + c.__name__ + "\n"
        return result

    def __len__(self):
        return len(self._classes)
