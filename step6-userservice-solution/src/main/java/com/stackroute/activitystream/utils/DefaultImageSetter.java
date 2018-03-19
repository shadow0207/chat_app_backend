package com.stackroute.activitystream.utils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.*;

import javax.mail.Multipart;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.activitystream.model.User;

public class DefaultImageSetter implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		/*File fnew=new File("/tmp/rose.jpg");
		BufferedImage originalImage=ImageIO.read(fnew);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos );
		byte[] imageInByte=baos.toByteArray();*/


		try {

			File fi = new File("download.jpg");
			byte[] fileContent = Files.readAllBytes(fi.toPath());
			System.err.println(fileContent);
			return fileContent;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
}