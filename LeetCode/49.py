class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagrams = collections.defaultdict(list)
        for string in strs:
            chars = [c for c in string]
            chars.sort()
            key = "".join(chars)
            anagrams[key].append(string)
        return anagrams.values()
