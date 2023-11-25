import unittest
from chunkify_function import chunkify


class TestChunkify(unittest.TestCase):
    def test_normal_usage(self):
        self.assertEqual(chunkify("ABCDEFG", 3, "x"), ["ABC", "DEF", "Gxx"])

    def test_empty_string(self):
        self.assertEqual(chunkify("", 3, "x"), [])

    def test_fill_character(self):
        self.assertEqual(chunkify("ABCDEFG", 3, "y"), ["ABC", "DEF", "Gyy"])

    def test_chunk_length_greater_than_string_length(self):
        self.assertEqual(chunkify("ABC", 5, "x"), ["ABCxx"])

    def test_chunk_length_equal_to_string_length(self):
        self.assertEqual(chunkify("ABC", 3, "x"), ["ABC"])

    def test_chunk_length_less_than_one(self):
        self.assertEqual(chunkify("ABC", 0, "x"), [])


if __name__ == "__main__":
    unittest.main()
