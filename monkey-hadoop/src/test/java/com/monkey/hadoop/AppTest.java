package com.monkey.hadoop;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import com.monkey.hadoop.mapper.MaxTemperatureMapper;
import com.monkey.hadoop.reducer.MaxTemperatureReducer;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase{
	public void testApp(String[] args) throws IllegalArgumentException, IOException {
		if (args.length != 2) {
			System.err.print(".........");
			System.exit(-1);
		}
		Job job = new Job();
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		try {
			job.waitForCompletion(true);
		} catch (ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
