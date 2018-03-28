set ns [new Simulator]

set f [open out.tr w]
$ns trace-all $f
set nf [open out.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
set n7 [$ns node]
set n8 [$ns node]
set n9 [$ns node]
set n10 [$ns node]
set n11 [$ns node]


$ns duplex-link $n7 $n1 10Mb 20ms RED
$ns duplex-link $n8 $n1 10Mb 20ms RED
$ns duplex-link $n9 $n1 10Mb 20ms RED
$ns duplex-link $n10 $n1 10Mb 20ms RED
$ns duplex-link $n11 $n1 10Mb 20ms RED

$ns duplex-link $n2 $n0 10Mb 20ms RED
$ns duplex-link $n3 $n0 10Mb 20ms RED
$ns duplex-link $n4 $n0 10Mb 20ms RED
$ns duplex-link $n5 $n0 10Mb 20ms RED
$ns duplex-link $n6 $n0 10Mb 20ms RED

$ns duplex-link $n0 $n1 5Mb 10ms RED


set tcp1 [new Agent/TCP]
set tcpsink1 [new Agent/TCPSink]
$ns attach-agent $n7 $tcp1
$ns attach-agent $n1 $tcpsink1

set tcp2 [new Agent/TCP]
set tcpsink2 [new Agent/TCPSink]
$ns attach-agent $n8 $tcp2
$ns attach-agent $n1 $tcpsink2

set tcp3 [new Agent/TCP]
set tcpsink3 [new Agent/TCPSink]
$ns attach-agent $n9 $tcp3
$ns attach-agent $n1 $tcpsink3

set tcp4 [new Agent/TCP]
set tcpsink4 [new Agent/TCPSink]
$ns attach-agent $n10 $tcp4
$ns attach-agent $n1 $tcpsink4

set tcp5 [new Agent/TCP]
set tcpsink5 [new Agent/TCPSink]
$ns attach-agent $n11 $tcp5
$ns attach-agent $n1 $tcpsink5

$n7 color Red
$n8 color Red
$n9 color Red
$n10 color Red
$n11 color Red

$n0 color green
$n1 color green

set tcpp1 [new Agent/TCP]
set tcppsink1 [new Agent/TCPSink]
$ns attach-agent $n0 $tcpp1
$ns attach-agent $n2 $tcppsink1

set tcpp2 [new Agent/TCP]
set tcppsink2 [new Agent/TCPSink]
$ns attach-agent $n0 $tcpp2
$ns attach-agent $n3 $tcppsink2

set tcpp3 [new Agent/TCP]
set tcppsink3 [new Agent/TCPSink]
$ns attach-agent $n0 $tcpp3
$ns attach-agent $n4 $tcppsink3

set tcpp4 [new Agent/TCP]
set tcppsink4 [new Agent/TCPSink]
$ns attach-agent $n0 $tcpp4
$ns attach-agent $n5 $tcppsink4

set tcpp5 [new Agent/TCP]
set tcppsink5 [new Agent/TCPSink]
$ns attach-agent $n0 $tcpp5
$ns attach-agent $n6 $tcppsink5


set tcp [new Agent/TCP]
set tcpsink [new Agent/TCPSink]
$ns attach-agent $n1 $tcp
$ns attach-agent $n0 $tcpsink

$ns color 20 green
$tcp set fid_ 20
$ns connect $tcp $tcpsink

$ns color 1 Red
$tcp1 set fid_ 1
$ns connect $tcp1 $tcpsink1
$ns color 2 Red
$tcp2 set fid_ 2
$ns connect $tcp2 $tcpsink2
$ns color 3 Red
$tcp3 set fid_ 3
$ns connect $tcp3 $tcpsink3
$ns color 4 Red
$tcp4 set fid_ 4
$ns connect $tcp4 $tcpsink4
$ns color 5 Red
$tcp5 set fid_ 5
$ns connect $tcp5 $tcpsink5


$ns color 11 Blue
$tcp1 set fid_ 11
$ns connect $tcpp1 $tcppsink1
$ns color 12 Blue
$tcp2 set fid_ 12
$ns connect $tcpp2 $tcppsink2
$ns color 13 Blue
$tcp3 set fid_ 13
$ns connect $tcpp3 $tcppsink3
$ns color 14 Blue
$tcp4 set fid_ 14
$ns connect $tcpp4 $tcppsink4
$ns color 15 Blue
$tcp5 set fid_ 15
$ns connect $tcpp5 $tcppsink5


set ftp [new Application/FTP]
$ftp attach-agent $tcp


set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
set ftp3 [new Application/FTP]
$ftp3 attach-agent $tcp3
set ftp4 [new Application/FTP]
$ftp4 attach-agent $tcp4
set ftp5 [new Application/FTP]
$ftp5 attach-agent $tcp5

set ftpp1 [new Application/FTP]
$ftpp1 attach-agent $tcpp1
set ftpp2 [new Application/FTP]
$ftpp2 attach-agent $tcpp2
set ftpp3 [new Application/FTP]
$ftpp3 attach-agent $tcpp3
set ftpp4 [new Application/FTP]
$ftpp4 attach-agent $tcpp4
set ftpp5 [new Application/FTP]
$ftpp5 attach-agent $tcpp5


proc finish {} {
global ns f nf
$ns flush-trace
close $f
close $nf
exit
}
$ns at 0.5 " $ftp start "
$ns at 5.0 " $ftp stop "

$ns at 0.5 " $ftp1 start "
$ns at 5.0 " $ftp1 stop "
$ns at 1.0 " $ftp2 start "
$ns at 5.0 " $ftp2 stop "
$ns at 1.0 " $ftp3 start "
$ns at 5.0 " $ftp3 stop "
$ns at 0.5 " $ftp4 start "
$ns at 5.0 " $ftp4 stop "
$ns at 1.5 " $ftp5 start "
$ns at 5.0 " $ftp5 stop "

$ns at 0.5 " $ftpp1 start "
$ns at 5.0 " $ftpp1 stop "
$ns at 0.5 " $ftpp2 start "
$ns at 5.0 " $ftpp2 stop "
$ns at 0.5 " $ftpp3 start "
$ns at 5.0 " $ftpp3 stop "
$ns at 0.5 " $ftpp4 start "
$ns at 5.0 " $ftpp4 stop "
$ns at 0.5 " $ftpp5 start "
$ns at 5.0 " $ftpp5 stop "

$ns at 6.0 " finish "
$ns run
