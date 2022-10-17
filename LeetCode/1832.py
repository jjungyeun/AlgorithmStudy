class Solution:
		# solve with collections.Counter
    def checkIfPangram1(self, sentence: str) -> bool:
        return len(collections.Counter(sentence)) == 26

		# solve with for loop
    def checkIfPangram2(self, sentence: str) -> bool:
        cnt = 26
        alp_dict = dict()
        for c in sentence:
            if c not in alp_dict:
                alp_dict[c] = True
                cnt -= 1
            if cnt == 0:
                return True
        
        return False
