#### 1. 처음 풀이 ####
class Node:
    def __init__(self, val):
        self.value = val
        self.data = None
        self.children = []


class Trie:
    def __init__(self):
        self.head = Node(None)

    def insert(self, word: str) -> None:
        cur = self.head
        for s in word:
            is_existed = False
            for child_node in cur.children:
                if child_node.value == s:
                    is_existed = True
                    cur = child_node
                    break
            if not is_existed:
                new_node = Node(s)
                cur.children.append(new_node)
                cur = new_node
        
        cur.data = word

    def search(self, word: str) -> bool:
        cur = self.head
        for s in word:
            for child_node in cur.children:
                if child_node.data == word:
                    return True
                if child_node.value == s:
                    cur = child_node
                    break
        return False
        

    def startsWith(self, prefix: str) -> bool:
        cur = self.head
        for s in prefix:
            is_existed = False
            for child_node in cur.children:
                if child_node.value == s:
                    cur = child_node
                    is_existed = True
                    break
            
            if not is_existed:
                return False
        return True


#### 2. 개선 풀이 ####
class Node:
    def __init__(self):
        self.data = None
        self.children = {}


class Trie:
    def __init__(self):
        self.head = Node()

    def insert(self, word: str) -> None:
        cur = self.head
        for s in word:
            if s in cur.children.keys():
                cur = cur.children[s]
            else:
                new_node = Node()
                cur.children[s] = new_node
                cur = new_node
        
        cur.data = word

    def search(self, word: str) -> bool:
        cur = self.head
        for s in word:
            if s in cur.children.keys():
                if cur.children[s].data == word:
                    return True
                cur = cur.children[s]
        return False
        

    def startsWith(self, prefix: str) -> bool:
        cur = self.head
        for s in prefix:
            if s in cur.children.keys():
                cur = cur.children[s]
            else:
                return False
        return True
