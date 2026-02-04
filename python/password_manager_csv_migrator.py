#!/usr/bin/env python3

"""
Password Manager CSV Migration Utility

This script converts CSV exports between popular password managers:
- KeePassXC
- Bitwarden
- Proton Pass

Supported conversions:
- KeePassXC to Proton Pass (Generic CSV)
- KeePassXC to Bitwarden
- Bitwarden to KeePassXC

Usage example:
    python password_manager_csv_migrator.py keepassxc protonpass keepassxc.csv protonpass.csv
    python password_manager_csv_migrator.py keepassxc bitwarden keepassxc.csv bitwarden.csv
    python password_manager_csv_migrator.py bitwarden keepassxc bitwarden.csv keepassxc.csv



The script preserves core login fields including usernames, passwords,
URLs, notes, TOTP seeds, and folder/vault structure where supported.

Intended for one-time migrations or batch conversions using official
CSV export/import formats.
"""
import csv
import argparse
import sys


PROTONPASS_HEADERS = [
    "name", "url", "email", "username",
    "password", "note", "totp", "vault",
]

BITWARDEN_HEADERS = [
    "folder", "favorite", "type", "name",
    "notes", "fields", "reprompt",
    "login_uri", "login_username",
    "login_password", "login_totp",
]

KEEPASSXC_HEADERS = [
    "Group", "Title", "Username", "Password",
    "URL", "Notes", "TOTP", "Icon",
    "Last Modified", "Created",
]



def map_keepassxc_to_bitwarden(keepassxc_row: dict) -> dict:
    """Converts a KeePassXC row to a Bitwarden row.
    Args:
        keepassxc_row (dict): A row from a KeePassXC CSV file.
    Returns:
        dict: A row corresponding to a Bitwarden CSV file.
    """
    entry_type = "login" if keepassxc_row["Username"] and keepassxc_row["Password"] else "note"
    bitwarden_row = {
        "folder": keepassxc_row["Group"],
        "favorite": 0,
        "type": entry_type,
        "name": keepassxc_row["Title"],
        "notes": keepassxc_row["Notes"],
        "fields": "",
        "reprompt": "",
        "login_uri": keepassxc_row["URL"],
        "login_username": keepassxc_row["Username"],
        "login_password": keepassxc_row["Password"],
        "login_totp": keepassxc_row["TOTP"],
    }

    return bitwarden_row


def map_keepassxc_to_protonpass(keepassxc_row: dict) -> dict:
    """Converts a KeePassXC row to a ProtonPass row.
    Args:
        keepassxc_row (dict): A row from a KeePassXC CSV file.
    Returns:
        dict: A row corresponding to a ProtonPass CSV file.
    """
    protonpass_row = {
        "name": keepassxc_row["Title"],
        "url": keepassxc_row["URL"],
        "email": keepassxc_row["Username"] if "@" in keepassxc_row["Username"] else "",
        "username": keepassxc_row["Username"],
        "password": keepassxc_row["Password"],
        "note": keepassxc_row["Notes"],
        "totp": keepassxc_row["TOTP"],
        "vault": keepassxc_row["Group"].replace("Root/", ""),
    }
    return protonpass_row


def convert_keepassxc_to_protonpass(input_filepath: str, output_filepath: str) -> None:
    """Converts a KeePassXC CSV export to a ProtonPass Generic CSV import."""
    with open(input_filepath, mode="r", newline="", encoding="utf-8") as infile, open(
        output_filepath, mode="w", newline="", encoding="utf-8"
    ) as outfile:

        reader = csv.DictReader(
            infile,
            quotechar='"',
            delimiter=",",
            quoting=csv.QUOTE_ALL,
            skipinitialspace=True,
        )
        writer = csv.DictWriter(
            outfile,
            fieldnames=PROTONPASS_HEADERS,
            quotechar='"',
            delimiter=",",
            quoting=csv.QUOTE_ALL,
        )
        writer.writeheader()

        for row in reader:
            # Ignore rows with Group "Recycle Bin"
            if row["Group"] == "Recycle Bin":
                continue

            protonpass_row = map_keepassxc_to_protonpass(row)
            writer.writerow(protonpass_row)


def convert_keepassxc_to_bitwarden(input_filepath: str, output_filepath: str) -> None:
    """Converts a KeePassXC CSV export to a Bitwarden CSV import."""
    with open(input_filepath, mode="r", newline="", encoding="utf-8") as infile, open(
        output_filepath, mode="w", newline="", encoding="utf-8"
    ) as outfile:

        reader = csv.DictReader(
            infile,
            quotechar='"',
            delimiter=",",
            quoting=csv.QUOTE_ALL,
            skipinitialspace=True,
        )
        writer = csv.DictWriter(
            outfile,
            fieldnames=BITWARDEN_HEADERS,
            quotechar='"',
            delimiter=",",
            quoting=csv.QUOTE_ALL,
        )
        writer.writeheader()

        for row in reader:
            # Ignore rows with Group "Recycle Bin"
            if row["Group"] == "Recycle Bin":
                continue
            # skip entry where title is Mailvelope
            if row["Title"] == "Mailvelope":
                continue

            bitwarden_row = map_keepassxc_to_bitwarden(row)
            writer.writerow(bitwarden_row)


def convert_bitwarden_to_keepassxc(input_filepath: str, output_filepath: str) -> None:
    """Converts a Bitwarden CSV export to a KeePassXC CSV import."""
    with open(input_filepath, newline='', encoding="utf-8") as bw, \
     open(output_filepath, "w", newline='', encoding="utf-8") as kp:

        reader = csv.DictReader(bw)
        writer = csv.writer(kp, quoting=csv.QUOTE_ALL)

        writer.writerow([
            "Group","Title","Username","Password",
            "URL","Notes","TOTP","Icon","Last Modified","Created"
        ])

        for row in reader:
            writer.writerow([
                row.get("folder", ""),
                row.get("name", ""),
                row.get("login_username", ""),
                row.get("login_password", ""),
                row.get("login_uri", ""),
                row.get("notes", ""),
                row.get("login_totp", ""),
                "", "", ""
            ])



# CLI
def main():
    parser = argparse.ArgumentParser(
        description="Password Manager CSV Migration Tool"
    )
    parser.add_argument(
        "source",
        choices=["keepassxc", "bitwarden"],
        help="Source password manager"
    )
    parser.add_argument(
        "target",
        choices=["protonpass", "bitwarden", "keepassxc"],
        help="Target password manager"
    )
    parser.add_argument("input", help="Input CSV file")
    parser.add_argument("output", help="Output CSV file")

    args = parser.parse_args()

    if args.source == "keepassxc" and args.target == "protonpass":
        keepassxc_to_protonpass(args.input, args.output)
    elif args.source == "keepassxc" and args.target == "bitwarden":
        keepassxc_to_bitwarden(args.input, args.output)
    elif args.source == "bitwarden" and args.target == "keepassxc":
        bitwarden_to_keepassxc(args.input, args.output)
    else:
        print("Unsupported conversion", file=sys.stderr)
        sys.exit(1)

    print(f"Conversion complete: {args.source} to {args.target}")

if __name__ == "__main__":
    main()

