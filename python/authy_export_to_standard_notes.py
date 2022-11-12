"""
This module parses the JSON file produced by this 
gist (https://gist.github.com/gboudreau/94bb0c11a6209c82418d01a59d958c93).

It converts the contents of the JSON file into a format that can be imported
directly into Standard Notes (https://standardnotes.org/).

I posted a gist on how to use this script here:
https://gist.github.com/talk2bryan/160cfc0c073990090d9117beda19871d
"""
import json
from typing import Dict, List


def main():
    totp_results: List[Dict[str, str]] = []
    with open("authy-to-bitwarden-export.json") as f:
        data = json.load(f)
        for item_dict in data["items"]:
            name: str = item_dict["name"]
            notes = item_dict["notes"]
            if len(name.split(":")) == 1:
                service = name
                account = ""
            else:
                service, account = name.split(":")
            totp = item_dict["login"]["totp"]
            secret: str = totp[totp.find("secret=") + len("secret=") : totp.find("&")]

            totp_results.append(
                {
                    "service": service.strip(),
                    "account": account.strip(),
                    "secret": secret,
                    "notes": "" if notes is None else notes,
                }
            )

    print("[", end="")
    separator = ","
    count = 0
    for item in totp_results:
        if count == len(totp_results) - 1:
            separator = "\\n"
        print(
            f"\\n  {{\\n    \\\"service\\\": \\\"{item['service']}\\\",\\n    \\\"account\\\": \\\"{item['account']}\\\",\\n    \\\"secret\\\": \\\"{item['secret']}\\\",\\n    \\\"notes\\\": \\\"{item['notes']}\\\"\\n  }}{separator}",
            end="",
        )
        count += 1
    print("]")


if __name__ == "__main__":
    main()
