package com.mink.memo.memo.service;

import com.mink.memo.memo.domain.Memo;
import com.mink.memo.memo.repository.MemoRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    //생성자가 여러개면 이렇게 하나면 AutoWird 사용 가능
    public MemoService(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }

    public boolean createMemo(long userId, String title, String contents){

        Memo memo = Memo.builder().
                userId(userId).
                title(title).
                contents(contents).
                build();

        try{
            memoRepository.save(memo);
        }catch (DataAccessException e){
            return false;
        }

        return true;


    }

    public List<Memo> getMemoList(long userId){
        return memoRepository.findByUserId(userId, Sort.by("id").descending());
    }

    public Memo getMemo(long id){
        Optional <Memo> optionalMemo = memoRepository.findById(id);

        return optionalMemo.get();

    }

}
