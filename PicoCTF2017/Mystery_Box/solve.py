from enigma.machine import EnigmaMachine
 
machine = EnigmaMachine.from_key_sheet(
        # We use rotors 1, 2 and 3, in that order
	rotors='I II III',
        # Umkehrwalze
        reflector='B',
        # Ringstellung or starting position for the alphabet wheel
	ring_settings='L O G',
        # Steckerbrett or connection board
	plugboard_settings='GL HF'
        )
 
# Grundstellung or rotors starting position
machine.set_display('PPP')
 
flag = machine.process_text("PXQQTAMYYDBCWGYELVN")
 
print(flag)