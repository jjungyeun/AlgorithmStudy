class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        main_inc, tmp_inc = [], []
        for num in nums:
						# main 배열이 비어있으면 현재 num 추가
            if len(main_inc) == 0:
                main_inc.append(num)
            
						# main 배열에 숫자가 1개만 있을 때
						# 현재 숫자가 더 크면 뒤에 추가, 더 작으면 대체
            elif len(main_inc) == 1:
                if main_inc[0] < num:
                    main_inc.append(num)
                elif num < main_inc[0]:
                    main_inc[0] = num

            # main 배열이 가득 찼을 때
            elif len(main_inc) == 2:
								# 현재 num이 가장 크면 조건을 만족하므로 True 반환
                if main_inc[1] < num:
                    return True
								# 현재 num이 가장 크지 않으면
                elif num < main_inc[1]:
										# 임시 배열이 비어있으면 현재 num 추가
                    if len(tmp_inc) == 0:
                        tmp_inc.append(num)
                    
										# 임시 배열에 숫자가 1개만 있고 현재 num이 더 크면 뒤에 추가
                    elif len(tmp_inc)==1 and num > tmp_inc[0]:
                        tmp_inc.append(num)
                    
										# 임시 배열이 가득 찼을 때는 main 배열과 비교
                    if len(tmp_inc) == 2:
												# main 배열의 작은 숫자가 임시 배열보다 작으면 True 반환
                        if main_inc[0] < tmp_inc[0]:
                            return True
												# main 배열의 큰 숫자보다 임시 배열의 큰 숫자가 작으면
												# main 배열을 임시 배열로 교체
                        if main_inc[1] > tmp_inc[1]:
                            main_inc = [tmp_inc[0], tmp_inc[1]]
                            tmp_inc.clear()

        # 조건을 만족하는 경우가 없으면 False 반환
        return False