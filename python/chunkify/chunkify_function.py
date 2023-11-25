# Groups characters from an input string into fixed length chunks
# with a given fill value
# Example:
# chunkify(‘ABCDEFG’, 3, ‘x’) -> [‘ABC’, ‘DEF’, Gxx’]
def chunkify(string: str, chunck_length: int, fill="x") -> list:
    if not string or chunck_length < 1:
        return []
    return [
        string[i : i + chunck_length].ljust(chunck_length, fill)
        for i in range(0, len(string), chunck_length)
    ]
