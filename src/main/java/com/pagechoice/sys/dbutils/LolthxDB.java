package com.pagechoice.sys.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.pagechoice.sys.logs.BaseLog;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public final class LolthxDB extends BaseLog
{

	private QueryRunner runner;
	private DataSource ds;

	public LolthxDB()
	{
		try
		{
			this.init();
		}
		catch (Exception ex)
		{
			this.log.error("DB.class初始化失败", ex);
		}
	}

	public void init() throws IOException, Exception
	{
		String dbfile = "lolthdb.properties";

		// 加载配置文件
		Properties p = new Properties();
		this.log.info("DB load properties[ {} ]..", dbfile);
		p.load(new FileInputStream(this.getClass().getClassLoader().getResource(dbfile).getPath()));
		// 构造queryRunner
		this.ds = BasicDataSourceFactory.createDataSource(p);
		this.runner = new QueryRunner(ds);
	}

	public void destory()
	{
		//TODO ...貌似可能数据库连接不会及时断开
	}

	public QueryRunner getRunner()
	{
		return runner;
	}

	@SuppressWarnings("rawtypes")
	public final static ScalarHandler scaleHandler = new ScalarHandler()
	{
		@Override
		public Object handle(ResultSet rs) throws SQLException
		{
			Object obj = super.handle(rs);
			if (obj instanceof BigInteger)
			{
				return ((BigInteger) obj).longValue();
			}
			return obj;
		}
	};

}
