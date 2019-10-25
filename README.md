This README file contains information on the contents of the meta-rauc-qemu layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: <first dependency>
  branch: <branch name>

  URI: <second dependency>
  branch: <branch name>

  .
  .
  .

Patches
=======

Please submit any patches against the meta-rauc-qemu layer to the xxxx mailing list (xxxx@zzzz.org)
and cc: the maintainer:

Maintainer: XXX YYYYYY <xxx.yyyyyy@zzzzz.com>

Table of Contents
=================

  I. Adding the meta-rauc-qemu layer to your build
 II. Misc


I. Adding the meta-rauc-qemu layer to your build
=================================================

Run 'bitbake-layers add-layer meta-rauc-qemu'

II. Misc
========

--- replace with specific information about the meta-rauc-qemu layer ---

Add to `local.conf`:

```
MACHINE_FEATURES += "pcbios efi"
IMAGE_FSTYPES += "wic"
WKS_FILE = "qemux86-grub-efi.wks"
EFI_PROVIDER = "grub-efi"
IMAGE_INSTALL_append = " \
                grub \
                grub-efi \
"
EXTRA_IMAGEDEPENDS += "ovmf"
PREFERRED_RPROVIDER_virtual/grub-bootconf = "rauc-qemu-grubconf"
```

Start qemu with:

```
runqemu wic nographic ovmf
```

Inspired from [this Poky patch](https://lists.yoctoproject.org/pipermail/yocto/2018-November/043242.html).
