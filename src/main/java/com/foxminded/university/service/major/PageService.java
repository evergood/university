package com.foxminded.university.service.major;

import com.foxminded.university.dao.CrudDao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageService<T> {

    private static final int ELEMENTS_PER_PAGE = 5;

    private CrudDao<T> dao;

    public int getMaxPage() {
        int numOfEntities = dao.getNumOfEntities();
        return numOfEntities % ELEMENTS_PER_PAGE == 0 ?
                numOfEntities / ELEMENTS_PER_PAGE :
                numOfEntities / ELEMENTS_PER_PAGE + 1;
    }

    public List<T> getAllEntitiesByPage(int page) {
        int maxPage = getMaxPage();
        if (page < 0 || page > maxPage) {
            return dao.getAllEntities(1, ELEMENTS_PER_PAGE);
        } else {
            return dao.getAllEntities(page, ELEMENTS_PER_PAGE);
        }
    }
}
