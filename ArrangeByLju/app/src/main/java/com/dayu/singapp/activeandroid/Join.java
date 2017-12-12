package com.dayu.singapp.activeandroid;

import android.text.TextUtils;

public final class Join implements Sqlable {
	static enum JoinType {
		LEFT, OUTER, INNER, CROSS
	}

	private From mFrom;
	private Class<? extends Model> mType;
	private String mAlias;
	private JoinType mJoinType;
	private String mOn;
	private String[] mUsing;

	Join(From from, Class<? extends Model> table, JoinType joinType) {
		mFrom = from;
		mType = table;
		mJoinType = joinType;
	}

	public Join as(String alias) {
		mAlias = alias;
		return this;
	}

	public From on(String on) {
		mOn = on;
		return mFrom;
	}

	public From on(String on, Object... args) {
		mOn = on;
		mFrom.addArguments(args);
		return mFrom;
	}

	public From using(String... columns) {
		mUsing = columns;
		return mFrom;
	}

	@Override
	public String toSql() {
		StringBuilder sql = new StringBuilder();

		if (mJoinType != null) {
			sql.append(mJoinType.toString()).append(" ");
		}

		sql.append("JOIN ");
		sql.append(Cache.getTableName(mType));
		sql.append(" ");

		if (mAlias != null) {
			sql.append("AS ");
			sql.append(mAlias);
			sql.append(" ");
		}

		if (mOn != null) {
			sql.append("ON ");
			sql.append(mOn);
			sql.append(" ");
		}
		else if (mUsing != null) {
			sql.append("USING (");
			sql.append(TextUtils.join(", ", mUsing));
			sql.append(") ");
		}

		return sql.toString();
	}
}
