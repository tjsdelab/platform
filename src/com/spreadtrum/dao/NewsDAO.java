package com.spreadtrum.dao;

import java.util.List;

import com.spreadtrum.model.*;

public interface NewsDAO {
	public void saveNews(News news);
    public List<News> showLast10News();
}

