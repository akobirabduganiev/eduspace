package me.eduspace.enums.bucket;

public enum BucketName {
    PROFILE_IMAGE("eduspace-image-store");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
