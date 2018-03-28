set ns [new Simulator]
set f [open disp.tr w]
$ns trace-all $f
set nf [open disp.nam w]
$ns namtrace-all $nf


set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

$ns duplex-link $n0 $n4 1Mb 10ms RED
$ns duplex-link $n1 $n4 1Mb 10ms RED
$ns duplex-link $n2 $n4 1Mb 10ms RED
$ns duplex-link $n3 $n4 1Mb 10ms RED

set udp [new Agent/UDP]
$ns attach-agent $n0 $udp
set udpsink [new Agent/Null] 
$ns attach-agent $n3 $udpsink

$ns connect $udp $udpsink
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp 

set udpp [new Agent/UDP]
$ns attach-agent $n1 $udpp
set udppsink [new Agent/Null] 
$ns attach-agent $n2 $udppsink

$ns connect $udpp $udppsink
set cbrr [new Application/Traffic/CBR]
$cbrr attach-agent $udpp


proc finish {} {
	global ns f nf
$ns flush-trace
close $f
close $nf
exec nam disp.nam &
exit
}


$ns at 0.5 " $cbr start"
$ns at 0.5 " $cbrr start"
$ns at 10.5 " $cbr stop"
$ns at 10.5 " $cbrr stop"
$ns at 11.0 " finish "
$ns run
