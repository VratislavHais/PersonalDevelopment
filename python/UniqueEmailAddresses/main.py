from typing import List


def process_local_name(local_name: str) -> str:
    idx = local_name.find("+")
    if idx >= 0:
        local_name = local_name[:idx]
    return local_name.replace(".", "")


def numUniqueEmails(emails: List[str]) -> int:
    emails_dict = {}
    result = 0
    for email in emails:
        name, domain = email.split("@")
        if domain not in emails_dict:
            emails_dict[domain] = set()
        emails_dict[domain].add(process_local_name(name))
    for _, value in emails_dict.items():
        result += len(value)
    return result


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(numUniqueEmails(["+@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
                           "testemail+david@lee.tcode.com"]))
