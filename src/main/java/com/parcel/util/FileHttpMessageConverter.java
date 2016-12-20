package com.parcel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

/**
 * 파일 처리 Converter
 * @author user
 *
 */
public class FileHttpMessageConverter implements HttpMessageConverter<File> {

	private List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
	
	public FileHttpMessageConverter() {
		this.supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
	}
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return File.class.equals(clazz);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		// TODO Auto-generated method stub
		return supportedMediaTypes;
	}

	@Override
	public File read(Class<? extends File> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(File t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if (contentType == null) {
			contentType = MediaType.APPLICATION_OCTET_STREAM;
		}
		
		outputMessage.getHeaders().setContentType(contentType);
		FileInputStream fis = new FileInputStream(t);
		FileCopyUtils.copy(fis, outputMessage.getBody());
		fis.close();
		
	}

}
