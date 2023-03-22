class Solution:
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        for pos in range(len(flowerbed)):
            left, right = pos - 1, pos + 1
            if flowerbed[pos] == 0 and (left < 0 or flowerbed[left] == 0) and (right >= len(flowerbed) or flowerbed[right] == 0):
                flowerbed[pos] = 1
                n -= 1

            if n <= 0:
                return True
        return False
