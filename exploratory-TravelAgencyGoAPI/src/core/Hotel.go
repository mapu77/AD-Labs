package core

type Hotel struct {
	Id            int
	Name          string
	Chain       string
	Rooms int
	Street string
	Number   int
	Zip   string
	City   string
	Province   string
	Country   string
}

func newHotel(id int, name string, chain string, rooms int, street string, number int, zipcode string, city string, province string, country string) *Hotel {
	return &Hotel{Id: id, Name: name, Chain: chain, Rooms: rooms, Street: street, Number: number, City: city, Province: province, Country: country}
}

// Make a hotel persistent. Returns nil if ok, the produced error otherwise
func persistHotel(hotel *Hotel) error {
	return nil
}