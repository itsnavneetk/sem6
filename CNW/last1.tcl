set ns [new Simulator]

set f [open out.tr w]
$ns trace-all $f

set nf [open out.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms RED
$ns duplex-link $n1 $n2 1Mb 10ms RED
$ns duplex-link $n2 $n3 1Mb 10ms RED

set tcp [new Agent/TCP]
set tcpsink [new Agent/TCPSink]
$ns attach-agent $n0 $tcp
$ns attach-agent $n3 $tcpsink
$ns connect $tcp $tcpsink

set udp [new Agent/UDP]
set null [new Agent/Null]
$ns attach-agent $n1 $udp
$ns attach-agent $n2 $null
$ns connect $udp $null

set ftp [new Application/FTP]
$ftp attach-agent $tcp

set cbr [new Application/Traffic/CBR]

$cbr attach-agent $udp


proc finish {} {
global ns f nf 
$ns flush-trace
close $f
close $nf
exit
}

$ns color 1 green 
$tcp set fid_ 1

$ns at 0.5 " $ftp start"
$ns at 5.0 " $ftp stop"

$ns at 1.0 " $cbr start"
$ns at 5.5 " $cbr stop"
$ns at 6.0 " finish"
$ns run
