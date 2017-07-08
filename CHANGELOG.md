# macchiato/fs changelog

## 0.0.7-0.1.0

Bug fixing. We're bumping version to 0.1.0 even though there aren't any breaking changes to break from the 0.0.x versioning issues.

## 0.0.5, 0.0.6

BREAKING:
- New `fs.path` namespace, @yogthos moved several path-specific functions there.
- Moved `with-separator` to `fs.path`

### Other

- CircleCI integration

## 0.0.4 (20161218)

### Added
- Changelog
- New functions: read-dir-sync, with-separator, read-stream, write-stream, pipe