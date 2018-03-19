package com.stackroute.activitystream.utils;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.activitystream.model.User;

public class UserIDGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		String prefix = "US";

		try {

			String generatedId = prefix + generateRandomNumberString() + generateRandomNumberString();

			return generatedId;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String generateRandomNumberString() {
		int randValue = (int) (Math.random() * ((999 - 100) + 1)) + 100;
		return Integer.toString(randValue);
	}
}