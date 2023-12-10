package br.com.yurineves.backend.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CnabService {
  private final Path fileStorageLocation;
  private final JobLauncher launcher;
  private final Job job;

  public CnabService(@Value("${file.upload-dir}") String fileUploadDir,
      @Qualifier("jobLauncherAsync") JobLauncher launcher,
      Job job) {
    this.fileStorageLocation = Paths.get(fileUploadDir);
    this.launcher = launcher;
    this.job = job;
  }

  public void uploadCnabFile(MultipartFile file) throws Exception {
    var fileName = StringUtils.cleanPath(file.getOriginalFilename());
    var targetLocation = fileStorageLocation.resolve(fileName);
    file.transferTo(targetLocation);

    var jobParameters = new JobParametersBuilder()
        .addJobParameter(
            "cnab",
            file.getOriginalFilename(),
            String.class,
            true)
        .addJobParameter("cnabFile",
            "file:" + targetLocation.toString(), String.class, false)
        .toJobParameters();

    launcher.run(job, jobParameters);
  }
}
