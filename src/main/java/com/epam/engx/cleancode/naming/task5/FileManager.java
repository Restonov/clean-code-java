

package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.predicates.FileExtensionPredicate;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class FileManager {
    private static final String[] IMAGE_EXTENSIONS = {"jpg", "png"};
    private static final String[] DOCUMENT_EXTENSIONS = {"pdf", "doc"};
    private String basePath = PropertyUtil.loadProperty("basePath");

    public File receiveFile(String fileName) {
        validateFileType(fileName);
        final String directoryPath = basePath + File.separator;
        return Paths.get(directoryPath, fileName).toFile();
    }

    public List<String> findAllImages() {
        return findByExtensions(basePath, IMAGE_EXTENSIONS);
    }

    public List<String> findAllDocuments() {
        return findByExtensions(basePath, DOCUMENT_EXTENSIONS);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImageType(fileName) && isInvalidDocumentType(fileName);
    }

    private boolean isInvalidImageType(String fileName) {
        FileExtensionPredicate imageExtensionsPredicate = new FileExtensionPredicate(IMAGE_EXTENSIONS);
        return !imageExtensionsPredicate.test(fileName);
    }

    private boolean isInvalidDocumentType(String fileName) {
        FileExtensionPredicate documentExtensionsPredicate = new FileExtensionPredicate(DOCUMENT_EXTENSIONS);
        return !documentExtensionsPredicate.test(fileName);
    }

    private List<String> findByExtensions(String directoryPath, String[] allowedExtensions) {
        final FileExtensionPredicate predicate = new FileExtensionPredicate(allowedExtensions);
        return Arrays.asList(
                Objects.requireNonNull(receiveDirectory(directoryPath)
                        .list(receiveFilenameFilter(predicate)))
        );
    }

    private FilenameFilter receiveFilenameFilter(final FileExtensionPredicate predicate) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File directory, String fileName) {
                return predicate.test(fileName);
            }
        };
    }

    private File receiveDirectory(String directoryPath) {
        File directoryInstance = new File(directoryPath);
        validateDirectory(directoryInstance);
        return directoryInstance;
    }

    private void validateDirectory(File directoryInstance) {
        if (isNotDirectory(directoryInstance)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directoryInstance.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File directoryInstance) {
        return !directoryInstance.isDirectory();
    }
}

