package MobileWare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class LatestDownloadedFile {
	public static void main(String[] args) {
		// Path to your downloads directory
		String downloadsDirPath = "C:\\Users\\User\\Downloads";
		
		int waitTime = 10;
		// Execution triggered time
		Instant executionStartTime = Instant.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
				.withZone(ZoneId.systemDefault());

		System.out.println("Execution start time: " + formatter.format(executionStartTime));
		String time = formatter.format(executionStartTime);
		LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		System.out.println(instant);
		boolean fileFound = false;

		try {
			while (Duration.between(executionStartTime, Instant.now()).getSeconds() < waitTime) {
				try {
					// Get the latest file in the downloads directory
					Optional<File> latestFileOpt = getLatestFile(downloadsDirPath);

					if (latestFileOpt.isPresent()) {
						File latestFile = latestFileOpt.get();

						// Get the last modified time of the latest file
						Path path = latestFile.toPath();
						BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
						FileTime lastModifiedTime = attributes.lastModifiedTime();
						Instant fileLastModifiedTime = lastModifiedTime.toInstant();
						String formattedLastModifiedTime = formatter.format(fileLastModifiedTime);

						// Check if the file's last modified time is after the execution start time
						if (fileLastModifiedTime.isAfter(executionStartTime)) {
							System.out.println("Latest downloaded file path: " + latestFile.getAbsolutePath());
							System.out.println("Last modified time: " + formattedLastModifiedTime);
							fileFound = true;
							break;
						}
					}
				} catch (IOException e) {
					System.out.println("An error occurred while retrieving the latest downloaded file.");
					e.printStackTrace();
				}
			}
			if (!fileFound) {
				System.out.println("No new file found within "+waitTime+" seconds.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Execution triggered time
		Instant executionEndTime = Instant.now();

		System.out.println("Execution end time: " + formatter.format(executionEndTime));

	}

	private static Optional<File> getLatestFile(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists() || !directory.isDirectory()) {
			return Optional.empty();
		}

		File[] files = directory.listFiles();
		if (files == null || files.length == 0) {
			return Optional.empty();
		}

		// Find the latest file based on the last modified time
		return Optional.ofNullable(Arrays.stream(files).filter(File::isFile)
				.max(Comparator.comparingLong(File::lastModified)).orElse(null));
	}
}