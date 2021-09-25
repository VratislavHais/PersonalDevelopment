from words import get_random_word


def get_masked_list(word: str):
    masked_list = []
    for c in word:
        if c.isalpha():
            masked_list.append("_")
        else:
            masked_list.append(c)
    return masked_list


def guess_and_add_to_set(guessed_set: set) -> str:
    guessed_character = input("Guess character: ").lower()
    while guessed_character in guessed_set or not guessed_character.isalpha() or len(guessed_character) > 1:
        guessed_character = input("Guess character: ").lower()
    guessed_set.add(guessed_character)
    return guessed_character


def reveal(masked_list: list, word: str, character: str):
    for i, c in enumerate(word):
        if c == character:
            masked_list[i] = c


def log(was_wrong: bool, guessed_characters: set, mistakes_so_far: int, max_mistakes=3):
    if was_wrong:
        print("Wrong")
    else:
        print("Correct")
    print("Used characters:", guessed_characters)
    print(f"Mistakes: {mistakes_so_far}/{max_mistakes}")


if __name__ == '__main__':
    guessed = set()
    max_mistakes = 4
    mistakes = 0
    word_to_guess = get_random_word()
    masked = get_masked_list(word_to_guess)
    while "_" in masked and mistakes < max_mistakes:
        print("".join(masked))
        guessed_char = guess_and_add_to_set(guessed)
        if guessed_char in word_to_guess:
            reveal(masked, word_to_guess, guessed_char)
            log(False, guessed, mistakes)
        else:
            mistakes += 1
            log(True, guessed, mistakes)
    if mistakes > max_mistakes:
        print("Maximum number of mistakes reached. Guessed word was:")
    else:
        print("Congratulations! You have successfully guessed the word:")
    print(word_to_guess)
