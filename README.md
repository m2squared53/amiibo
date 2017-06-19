# amiibo
Java version of Amiibo NTAG

This is a work in progress which will attempt to extend the work of Kostia Plays and others. The ultimate goal is to port everything to
Java and eventully use Processing and the Arduino IDE to create Amiibo clones using commercially available blank NTAG215 NFC devices.

This should be doable and quite frankly much easier using Java instead of C and shell scripts to create the bin files for dumping to the
Arduino. The main challenge is getting the crypto correct but that doesn't seem to be too difficult since it appears to be AES/CBC. It 
also appears to use a 128 bit (16 byte) key. I'm not sure what the padding is but that should be simple enough to figure out.

